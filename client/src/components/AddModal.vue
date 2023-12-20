<template mode="ios">
    <ion-modal ref="modal" trigger="add-modal" @willDismiss="onWillDismiss">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-button @click="cancel()">取消</ion-button>
                </ion-buttons>
                <ion-title>添加餐馆</ion-title>
                <ion-buttons slot="end">
                    <ion-button @click="confirm()">确定</ion-button>
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content class="ion-padding">
            <ion-item>
                <ion-input label="餐馆名称" label-placement="start" ref="input" type="text"
                    v-model="restaurantName"></ion-input>
            </ion-item>
            <ion-item>
                <ion-select label="餐馆地点" v-model="location">
                    <ion-select-option v-for="item in locationList" :value="item">{{ item.name }}</ion-select-option>
                </ion-select>
            </ion-item>
            <ion-item>
                <ion-input label="餐馆描述" v-model="restaurantDescription" label-placement="start" type="text"></ion-input>
            </ion-item>
        </ion-content>
    </ion-modal>
    <ion-toast :message="toastMessage" :is-open="isToastOpen" :duration="5000"
        @didDismiss="setToastOpen(false)"></ion-toast>
</template>

<script setup lang="ts">
import { IonModal, IonSelect, IonSelectOption, IonHeader, IonToolbar, IonButton, IonButtons, IonTitle, IonContent, IonItem, IonInput, IonToast } from '@ionic/vue';
import { ref } from "vue";
import { OverlayEventDetail } from '@ionic/core/components';
import { Location, Restaurant } from '@/models/restaurant';
import Axios from '@/utils/axios';

var location: Location
var restaurantName: string
var restaurantDescription: string

const props = defineProps<{
    restaurant: Restaurant
    isModify: boolean
}>()

if (props.isModify == true) {
    console.log("yes")
} else {
    console.log("no")
}

const modal = ref();
const input = ref();
const isToastOpen = ref(false);
const setToastOpen = (state: boolean) => {
    isToastOpen.value = state;
};
const toastMessage = ref("");

const locationList = ref([] as Location[])
Axios("/location/list", null, "GET").then(res => {
    locationList.value = res as Location[]
}).catch(err => {
    console.log(err)
})

const cancel = () => modal.value.$el.dismiss(null, 'cancel');
const confirm = async () => {
    const restaurant: Restaurant = {
        name: restaurantName,
        locationId: location.id,
        locationName: location.name,
        description: restaurantDescription,
        userId: 1,
        pic: ""
    }
    await addRestaurant(restaurant)
    modal.value.$el.dismiss(null, 'confirm');
};

const addRestaurant = async (restaurant: Restaurant) => {
    Axios("/restaurant/add", restaurant, "post").then(res => {
        console.log(res)
        toastMessage.value = res as string
        setToastOpen(true)
    }).catch(err => {
        console.log(err)
    })
}



const onWillDismiss = (ev: CustomEvent<OverlayEventDetail>) => {
    if (ev.detail.role === 'confirm') {
    }
};
</script>