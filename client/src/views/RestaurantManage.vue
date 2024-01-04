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
                    <ion-item @click="postPic()">
                        <ion-label>餐馆图片</ion-label>
                        <ion-img class="w-[200px] h-[200px]" :src="modalData.pic"></ion-img>
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
import { IonModal, IonInput, IonSelect, IonImg, IonSelectOption, IonButton, IonHeader, IonList, IonRefresher, IonRefresherContent, IonItemGroup, IonItemDivider, IonItemSliding, IonItem, IonItemOption, IonItemOptions, IonLabel, IonTitle, IonToolbar, IonButtons, IonBackButton, IonContent, IonPage } from '@ionic/vue';
import { Camera, CameraResultType} from '@capacitor/camera';
import uploadImg from '@/assets/uploadImg.svg'
import UploadService from "@/services/upload"

// Modal
enum ModalType { Add, Modify }
const locationList = ref([] as Location[])
const getLocationList = async () => {
    locationList.value = await LocationService.listLocation();
}
var picBase64 = ""

const postPic = async () => {
    const image = await Camera.getPhoto({
        quality: 90,
        allowEditing: true,
        resultType: CameraResultType.Base64
    });
    picBase64 = image.base64String || ""
    modalData.value.pic = 'data:image/png;base64,' + image.base64String;
};

const openModal = (type: ModalType, r: Restaurant | null) => {
    getLocationList()
    switch (type) {
        case ModalType.Add:
            modalTitle.value = "添加餐馆"
            modalData.value = { pic: uploadImg }
            break
        // Todo
        case ModalType.Modify:
            modalTitle.value = "编辑餐馆"
            modalData.value = { ...r }
            modalData.value.pic = "http://localhost:8080/api"+r?.pic
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
var modalData = ref<ModalData>({
    name: '',
    id: 0,
    description: '',
    locationName: '',
    locationId: 0,
    pic: ''
})

var modalType: ModalType

const modal = ref();
const input = ref();
const isModalOpen = ref();
const modalTitle = ref("添加餐馆")


const cancel = () => {
    modal.value.$el.dismiss(null, 'cancel');
    isModalOpen.value = false;
}

const confirm = async () => {
    switch (modalType) {
        case ModalType.Add:
            var pic = ""
            if (picBase64 != undefined && picBase64 != "") {
                console.log(picBase64)
                pic = await UploadService.uploadImg(picBase64)
            }
            const { id, ...restaurant } = modalData.value
            console.log(pic)
            restaurant.pic = pic
            console.log(restaurant.pic)
            await addRestaurant(restaurant)
            break
        case ModalType.Modify:
            await updateRestaurant(modalData.value)
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