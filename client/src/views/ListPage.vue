<style scoped>
.example-content {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
}
</style>

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
                    <ion-label>{{ item.location?.name}}</ion-label>
                </ion-item-divider>

                <ion-item v-for="r in item.restaurantList">
                    <ion-label>{{ r.name }}</ion-label>
                </ion-item>
            </ion-item-group>
        </ion-content>
    </ion-page>
</template>

<script lang="ts" setup>
import Axios from "@/utils/axios"
import { IonHeader, IonToolbar, IonTitle, IonContent, IonPage, IonItem, IonItemDivider, IonItemGroup, IonLabel } from '@ionic/vue';
import { ref } from "vue";

interface Restaurant {
    id?: number
    name?: string
    pic?: string
    userId?: number
    description?: string
    locationId?: number
}

interface Location {
    id?: number
    name?: string
    description?: string
}

interface RestaurantLocation {
    location?: Location
    restaurantList?: Restaurant[]
}

const list = ref([] as RestaurantLocation[]);

Axios("/restaurant/list", null, "GET")
    .then((res: any) => {
        list.value = res
    }).catch((err) => {
        console.log(err)
    })

// const location = ref([
//     {
//         "name": "新食堂一楼",
//         "restaurant": [
//             { "name": "大伙" },
//             { "name": "自选" }
//         ]
//     },
//     {
//         "name": "新食堂二楼",
//         "restaurant": [
//             { "name": "板煮演义" },
//             { "name": "面" }
//         ]
//     },
//     {
//         "name": "旧食堂一楼",
//         "restaurant": [
//             { "name": "锋味美食" },
//             { "name": "面" }
//         ]
//     },
//     {
//         "name": "旧食堂二楼",
//         "restaurant": [
//             { "name": "小兔子" },
//             { "name": "涛涛美食" }
//         ]
//     },
//     {
//         "name": "新楼",
//         "restaurant": [
//             { "name": "希食东" },
//             { "name": "鸡本部" }
//         ]
//     },
//     {
//         "name": "图书馆底",
//         "restaurant": [
//             { "name": "冒菜" },
//             { "name": "面包店" }
//         ]
//     },
// ])




</script>