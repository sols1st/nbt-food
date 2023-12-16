<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-segment @ion-change="segmentValueChanged" :value="segmentValue">
                    <ion-segment-button value="restaurant">
                        <ion-label>餐馆</ion-label>
                    </ion-segment-button>
                    <ion-segment-button value="location">
                        <ion-label>地点</ion-label>
                    </ion-segment-button>
                    <ion-segment-button value="user">
                        <ion-label>用户</ion-label>
                    </ion-segment-button>
                </ion-segment>
            </ion-toolbar>
        </ion-header>
        <ion-content>
        </ion-content>
    </ion-page>
</template>


<script setup lang="ts">
import { IonHeader, IonLabel, IonSegment, IonSegmentButton, IonToolbar, IonContent, IonPage } from '@ionic/vue';
import { ref } from 'vue';
import Axios from '@/utils/axios';
import { Restaurant } from '@/models/restaurant'

var segmentValue = 'restaurant';

const segmentValueChanged = () => {
}






</script>