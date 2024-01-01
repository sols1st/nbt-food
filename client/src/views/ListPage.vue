<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-title>所有餐馆</ion-title>
            </ion-toolbar>
            <ion-toolbar>
                <ion-searchbar></ion-searchbar>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <ion-refresher slot="fixed" @ionRefresh="handleRefresh($event)">
                <ion-refresher-content></ion-refresher-content>
            </ion-refresher>
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

<script lang="ts"  setup>
import { IonHeader, IonRefresher,IonSearchbar, IonRefresherContent, IonToolbar, IonTitle, IonContent, IonPage, IonItem, IonItemDivider, IonItemGroup, IonLabel } from '@ionic/vue';
import { ref } from "vue";
import { RestaurantLocation } from "@/models/restaurant"
import RestaurantService from "@/services/restaurant"

const list = ref([] as RestaurantLocation[]);
const handleRefresh = async (event: CustomEvent) => {
    await getList();
    (event as any).target.complete();
};

const getList = async () => {
    list.value = await RestaurantService.listRestaurantGroupByLocation();
}

getList()
</script>