<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-title>管理页面</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content color="light">
            <ion-list :inset="true">
                <ion-item  :router-link="'/manage/restaurant'" router-direction="forward">
                    <ion-label>餐馆管理</ion-label>
                </ion-item>
                <ion-item>
                    <ion-label>地点管理</ion-label>
                </ion-item>
                <ion-item>
                    <ion-label>用户管理</ion-label>
                </ion-item>
            </ion-list>
        </ion-content>
    </ion-page>
</template>


<script setup lang="ts">
import { IonHeader, IonLabel, IonList, IonItem, IonTitle, IonToolbar, IonContent, IonPage } from '@ionic/vue';
// import { ref } from 'vue';
// import Axios from '@/utils/axios';
// import { Restaurant } from '@/models/restaurant'

</script>