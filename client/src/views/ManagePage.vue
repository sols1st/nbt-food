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
                <ion-title>随机食物</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <div class="flex flex-col justify-between h-full py-[50px]">
                <div>
                    <div class="flex flex-col justify-center items-center">
                        <div class="flex justify-center items-center">
                            <img class="h-40 w-40 rounded-full" :src="restaurant?.pic" alt="">
                        </div>
                        <div class="flex justify-center items-center">
                            <h1 class="text-2xl font-bold">{{ restaurant?.name }}</h1>
                        </div>
                        <div class="flex justify-center items-center">
                            <p class="text-sm text-gray-500">{{ restaurant?.description }}</p>
                        </div>
                    </div>
                </div>
                <div class="flex justify-center">
                    <ion-button @click="getRandom" expand="block" fill="outline" class="mb-[50px] mx-auto max-w-[500px]"
                        style="width:calc(100vw - 50px )"> 随机一下</ion-button>
                </div>
            </div>
        </ion-content>
    </ion-page>
</template>


<script setup lang="ts">
import { IonButton, IonHeader, IonToolbar, IonTitle, IonContent, IonPage } from '@ionic/vue';
import { ref } from 'vue';
import Axios from '@/utils/axios';
import { Restaurant } from '@/models/restaurant'

const restaurant = ref({} as Restaurant);

const getRandom = () => {
    console.log("get random")
    Axios("/restaurant/random", null, "GET")
        .then((res: any) => {
            res.pic = "http://192.168.10.233:8080/api/pic/FriDec15001548CST2023.jpg"
            res.description = "很好吃的地方"
            restaurant.value = res
            console.log(restaurant.value)
        }).catch(err => {
            console.log(err)
        }
        )
}




</script>