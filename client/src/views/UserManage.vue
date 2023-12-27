<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-title>用户管理</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <IonButton @click="Toast('Hello World')">
                TEST
            </IonButton>
        </ion-content>

    </ion-page>
</template>

<script setup lang="ts">

import { IonHeader, IonTitle, IonToolbar, IonContent, IonPage, IonButton } from '@ionic/vue';
import Toast from '@/utils/toast'


// Toast("Hello World")

</script>