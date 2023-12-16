<template>
    <ion-page mode="ios">
        <ion-tabs>
            <ion-router-outlet></ion-router-outlet>
            <ion-tab-bar slot="bottom">

                <ion-tab-button v-for="item in tabs" :tab="item.tab" :href="item.href">
                    <ion-icon :icon="item.icon" />
                    <ion-label>{{ item.label }}</ion-label>
                </ion-tab-button>

            </ion-tab-bar>
        </ion-tabs>
    </ion-page>
</template>
  
<script setup lang="ts">
import { IonPage, IonTabs, IonRouterOutlet, IonTabBar, IonTabButton, IonLabel, IonIcon } from '@ionic/vue';
import { fastFoodOutline, createOutline, diceOutline, personCircleOutline } from 'ionicons/icons';

interface tab {
    tab: string,
    href: string,
    icon: string,
    label: string
}

const tabs: tab[] = [
    {
        tab: "random",
        href: "/random",
        icon: diceOutline,
        label: "随机"
    },
    {
        tab: "list",
        href: "/list",
        icon: fastFoodOutline,
        label: "所有"
    },
    {
        tab: "search",
        href: "/search",
        icon: personCircleOutline,
        label: "个人资料"
    },
    {
        tab: "manage",
        href: "/detail/1",
        icon: createOutline ,
        label: "管理"
    },
]

</script>