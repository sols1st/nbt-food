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

            <!-- Modal -->
            <ion-modal mode="ios" :is-open="isOpen" ref="modal" trigger="add-modal" @willDismiss="onWillDismiss">
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
                            v-model="modalRestaurantName"></ion-input>
                    </ion-item>
                    <ion-item>
                        <ion-select label="餐馆地点" v-model="location">
                            <ion-select-option v-for="item in locationList" :value="item">{{ item.name
                            }}</ion-select-option>
                        </ion-select>
                    </ion-item>
                    <ion-item>
                        <ion-input label="餐馆描述" v-model="modalRestaurantDescription" label-placement="start"
                            type="text"></ion-input>
                    </ion-item>
                </ion-content>
            </ion-modal>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
import RestaurantService from "@/services/restaurant"
import LocationService from "@/services/location"
import { ref } from "vue";
import { RestaurantLocation, Restaurant } from "@/models/restaurant"
import { Location } from "@/models/location"
import { IonModal, IonInput, IonSelect, IonSelectOption, IonButton, IonHeader, IonList, IonRefresher, IonRefresherContent, IonItemGroup, IonItemDivider, IonItemSliding, IonItem, IonItemOption, IonItemOptions, IonLabel, IonTitle, IonToolbar, IonButtons, IonBackButton, IonContent, IonPage } from '@ionic/vue';

// Modal
var location: Location
var modalRestaurantName: string
var modalRestaurantDescription: string

const modal = ref();
const input = ref();
const isOpen = ref(false);

const locationList = ref([] as Location[])
const getLocationList = async () => {
    locationList.value = await LocationService.listLocation();
}
getLocationList()

const cancel = () => {
    modal.value.$el.dismiss(null, 'cancel');
}

const confirm = async () => {
    const restaurant: Restaurant = {
        name: modalRestaurantName,
        locationId: location.id,
        locationName: location.name,
        description: modalRestaurantDescription,
        userId: 1,
        pic: ""
    }
    await addRestaurant(restaurant)
    modal.value.$el.dismiss(null, 'confirm');
};

// Base
const list = ref([] as RestaurantLocation[]);

const getList = async () => {
    list.value = await RestaurantService.listRestaurantGroupByLocation();
}

const addRestaurant = async (r: Restaurant) => {
    await RestaurantService.addRestaurant(r);
    getList();
}

const removeRestaurant = async (r: Restaurant) => {
    await RestaurantService.removeRestaurant(r);
    getList();
}

const handleRefresh = async (event: CustomEvent) => {
    getList();
    (event as any).target.complete();
};

getList()

</script>