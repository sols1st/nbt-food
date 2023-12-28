<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button text="返回" default-href="/manage"></ion-back-button>
                </ion-buttons>
                <ion-title>用户管理</ion-title>
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
                        <ion-label>{{ item.username }}</ion-label>
                    </ion-item>
                    <ion-item-options>
                        <ion-item-option @click="openModal(ModalType.Modify, item)">编辑</ion-item-option>
                        <ion-item-option @click="removeUser(item)" color="danger">删除</ion-item-option>
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
import UserService from "@/services/user"
import { ref } from "vue";
import { User } from "@/models/user"
import { IonModal, IonInput, IonButton, IonHeader, IonList, IonRefresher, IonRefresherContent, IonItemSliding, IonItem, IonItemOption, IonItemOptions, IonLabel, IonTitle, IonToolbar, IonButtons, IonBackButton, IonContent, IonPage } from '@ionic/vue';

const list = ref([] as User[])
const getList = async () => {
    list.value = await UserService.listUser();
}
const addUser = async (u: User) => {
    await UserService.addUser(u);
    getList();
}

const updateUser = async (u: User) => {
    await UserService.updateUser(u);
    getList();
}

const removeUser = async (u: User) => {
    await UserService.removeUser(u);
    getList();
}

const handleRefresh = async (event: CustomEvent) => {
    getList();
    (event as any).target.complete();
};

getList()

// Modal
enum ModalType { Add, Modify }
const openModal = (type: ModalType, u: User | null) => {
    switch (type) {
        case ModalType.Add:
            modalTitle.value = "添加用户"
            modalData = {}
            break
        case ModalType.Modify:
            modalTitle.value = "编辑用户"
            modalData = { ...u }
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
            const { id, ...user } = modalData
            await addUser(user)
            break
        case ModalType.Modify:
            await updateUser(modalData)
    }
    modal.value.$el.dismiss(null, 'confirm');
    isModalOpen.value = false;
};
</script>