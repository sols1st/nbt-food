<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button text="返回" default-href="/manage"></ion-back-button>
                </ion-buttons>
                <ion-title>地点管理</ion-title>
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
                <ion-item-sliding v-for="item in list">
                    <ion-item>
                        <ion-label>{{ item.name }}</ion-label>
                    </ion-item>
                    <ion-item-options>
                        <ion-item-option @click="openModal(ModalType.Modify, item)">编辑</ion-item-option>
                        <ion-item-option @click="removeLocation(item)" color="danger">删除</ion-item-option>
                    </ion-item-options>
                </ion-item-sliding>
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
                        <ion-input label="地点名称" label-placement="start" ref="input" type="text"
                            v-model="modalData.name"></ion-input>
                    </ion-item>
                    <ion-item>
                        <ion-input label="地点描述" v-model="modalData.description" label-placement="start"
                            type="text"></ion-input>
                    </ion-item>
                </ion-content>
            </ion-modal>
        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
import LocationService from "@/services/location"
import { ref } from "vue";
import { Location } from "@/models/location"
import { IonModal, IonInput, IonButton, IonHeader, IonList, IonRefresher, IonRefresherContent, IonItemSliding, IonItem, IonItemOption, IonItemOptions, IonLabel, IonTitle, IonToolbar, IonButtons, IonBackButton, IonContent, IonPage } from '@ionic/vue';

const list = ref([] as Location[])
const getList = async () => {
    list.value = await LocationService.listLocation();
}
const addLocation = async (l: Location) => {
    await LocationService.addLocation(l);
    getList();
}

const updateLocation = async (l: Location) => {
    await LocationService.updateLocation(l);
    getList();
}

const removeLocation = async (l: Location) => {
    await LocationService.removeLocation(l);
    getList();
}

const handleRefresh = async (event: CustomEvent) => {
    getList();
    (event as any).target.complete();
};

getList()

// Modal
enum ModalType { Add, Modify }
const openModal = (type: ModalType, l: Location | null) => {
    switch (type) {
        case ModalType.Add:
            modalTitle.value = "添加地点"
            modalData = {}
            break
        case ModalType.Modify:
            modalTitle.value = "编辑地点"
            modalData = { ...l }
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
}
var modalData: ModalData = {
    name: '',
    id: 0,
    description: '',
}

var modalType: ModalType

const modal = ref();
const input = ref();
const isModalOpen = ref();
const modalTitle = ref("添加地点")


const cancel = () => {
    modal.value.$el.dismiss(null, 'cancel');
    isModalOpen.value = false;
}

const confirm = async () => {
    switch (modalType) {
        case ModalType.Add:
            const { id, ...location } = modalData
            await addLocation(location)
            break
        case ModalType.Modify:
            await updateLocation(modalData)
    }
    modal.value.$el.dismiss(null, 'confirm');
    isModalOpen.value = false;
};
</script>