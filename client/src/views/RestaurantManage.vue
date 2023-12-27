<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button text="返回" default-href="/manage"></ion-back-button>
                </ion-buttons>
                <ion-title>餐馆管理</ion-title>
                <ion-buttons slot="end">
                    <ion-button @click="openModal(ModalType.Add, null)">添加</ion-button>
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
                            <ion-item-option @click="openModal(ModalType.Modify, r)">编辑</ion-item-option>
                            <ion-item-option @click="removeRestaurant(r)" color="danger">删除</ion-item-option>
                        </ion-item-options>
                    </ion-item-sliding>
                </ion-item-group>
            </ion-list>

            <!-- Modal -->
            <ion-modal mode="ios" :is-open="isModalOpen" ref="modal">
                <ion-header>
                    <ion-toolbar>
                        <ion-buttons slot="start">
                            <ion-button @click="cancel()">取消</ion-button>
                        </ion-buttons>
                        <ion-title>{{ modalTitle }}</ion-title>
                        <ion-buttons slot="end">
                            <ion-button @click="confirm()">确定</ion-button>
                        </ion-buttons>
                    </ion-toolbar>
                </ion-header>
                <ion-content class="ion-padding">
                    <ion-item>
                        <ion-input label="餐馆名称" label-placement="start" ref="input" type="text"
                            v-model="modalData.name"></ion-input>
                    </ion-item>
                    <ion-item>
                        <ion-select label="餐馆地点" v-model="modalData.locationId">
                            <ion-select-option v-for="item in locationList" :value="item.id">{{ item.name
                            }}</ion-select-option>
                        </ion-select>
                    </ion-item>
                    <ion-item>
                        <ion-input label="餐馆描述" v-model="modalData.description" label-placement="start"
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
enum ModalType { Add, Modify }
const locationList = ref([] as Location[])
const getLocationList = async () => {
    locationList.value = await LocationService.listLocation();
}
const openModal = (type: ModalType, r: Restaurant | null) => {
    getLocationList()
    switch (type) {
        case ModalType.Add:
            modalTitle.value = "添加餐馆"
            modalData = {}
            break
        case ModalType.Modify:
            modalTitle.value = "编辑餐馆"
            modalData = { ...r }
            break
        default:
            return
    }
    modalType = type
    isModalOpen.value = true;
}

interface ModalData {
    name?: string
    id?: number
    description?: string
    locationName?: string
    locationId?: number
    pic?: string
}
var modalData: ModalData = {
    name: '',
    id: 0,
    description: '',
    locationName: '',
    locationId: 0,
    pic: ''
}

var modalType: ModalType

const modal = ref();
const input = ref();
const isModalOpen = ref();
const modalTitle = ref("添加餐馆")


const cancel = () => {
    modal.value.$el.dismiss(null, 'cancel');
    isModalOpen.value = false;
}

// to add userId
const confirm = async () => {
    switch (modalType) {
        case ModalType.Add:
            const { id, ...restaurant } = modalData
            await addRestaurant(restaurant)
            break
        case ModalType.Modify:
            await updateRestaurant(modalData)
    }
    modal.value.$el.dismiss(null, 'confirm');
    isModalOpen.value = false;
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

const updateRestaurant = async (r: Restaurant) => {
    await RestaurantService.updateRestaurant(r);
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