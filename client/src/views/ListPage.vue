<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-title>所有餐馆</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <ion-item-group v-for="item in list">
                <ion-item-divider>
                    <ion-label>{{ item.location?.name }}</ion-label>
                </ion-item-divider>

                <ion-item v-for="r in item.restaurantList">
                    <ion-label :router-link="'/detail/' + r.id" router-direction="back">{{ r.name }}</ion-label>
                </ion-item>
            </ion-item-group>
        </ion-content>
    </ion-page>
</template>

<script lang="ts" setup>
import Axios from "@/utils/axios"
import { IonHeader, IonToolbar, IonTitle, IonContent, IonPage, IonItem, IonItemDivider, IonItemGroup, IonLabel } from '@ionic/vue';
import { ref } from "vue";
import { RestaurantLocation } from "@/models/restaurant"

if ('scrollRestoration' in history) {
    history.scrollRestoration = 'manual'
}

const list = ref([] as RestaurantLocation[]);

Axios("/restaurant/list", null, "GET")
    .then((res: any) => {
        list.value = res
    }).catch((err) => {
        console.log(err)
    })



</script>