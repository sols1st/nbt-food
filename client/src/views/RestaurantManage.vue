<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button text="返回" default-href="/manage"></ion-back-button>
                </ion-buttons>
                <ion-title>餐馆管理</ion-title>
                <ion-buttons slot="end">
                    <ion-button id="add-modal">添加</ion-button>
                </ion-buttons>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <ion-refresher slot="fixed" @ionRefresh="handleRefresh($event)">
                <ion-refresher-content></ion-refresher-content>
            </ion-refresher>
            <ion-list>
                <ion-item-group v-for="item in list">
                    <ion-item-divider>
                        <ion-label>{{ item.location?.name }}</ion-label>
                    </ion-item-divider>
                    <ion-item-sliding v-for="r in item.restaurantList">
                        <ion-item>
                            <ion-label :router-link="'/detail/' + r.id" router-direction="back">{{ r.name }}</ion-label>
                        </ion-item>
                        <ion-item-options>
                            <ion-item-option>编辑</ion-item-option>
                            <ion-item-option @click="removeRestaurant(r)" color="danger">删除</ion-item-option>
                        </ion-item-options>
                    </ion-item-sliding>
                </ion-item-group>
            </ion-list>
            <AddModal></AddModal>

            <ion-toast :message="toastMessage" :is-open="isToastOpen" :duration="5000"
                @didDismiss="setToastOpen(false)"></ion-toast>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
import Axios from "@/utils/axios"
import { ref } from "vue";
import { RestaurantLocation, Restaurant } from "@/models/restaurant"
import { IonButton, IonHeader, IonList,IonToast, IonRefresher, IonRefresherContent, IonItemGroup, IonItemDivider, IonItemSliding, IonItem, IonItemOption, IonItemOptions, IonLabel, IonTitle, IonToolbar, IonButtons, IonBackButton, IonContent, IonPage } from '@ionic/vue';
import AddModal from "@/components/AddModal.vue";

const isToastOpen = ref(false);
const setToastOpen = (state: boolean) => {
    isToastOpen.value = state;
};
const toastMessage = ref("");
const isModify = ref(false)

const removeRestaurant = (r: Restaurant) => {
    const url = "/restaurant/delete/" + r.id
    Axios(url, null, "delete")
        .then((res: any) => {
            toastMessage.value = res
            setToastOpen(true)
            getList()
        }).catch((err) => {
            toastMessage.value = "遇到错误"
            console.log(err)
        })
}

const handleRefresh = (event: CustomEvent) => {
    Axios("/restaurant/list", null, "GET")
        .then((res: any) => {
            list.value = res;
            (event as any).target.complete()
        }).catch((err) => {
            console.log(err)
        })
};

const list = ref([] as RestaurantLocation[]);

const getList = () => {
    Axios("/restaurant/list", null, "GET")
        .then((res: any) => {
            list.value = res
        }).catch((err) => {
            console.log(err)
        })
}

getList()

</script>