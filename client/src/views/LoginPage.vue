<template>
    <ion-page mode="ios">
        <ion-header v-if="isLoadFinished">
            <ion-toolbar>
                <ion-title>个人中心</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-header v-else>
            <ion-toolbar>
                <ion-title>加载中</ion-title>
                <ion-progress-bar type="indeterminate"></ion-progress-bar>
            </ion-toolbar>
        </ion-header>
        <ion-content v-if="isLoadFinished">
            <div class="flex flex-col justify-between h-full py-[50px]">
                <div v-if="isAuthenticated" class="flex flex-col justify-center items-center">
                    <div class="flex justify-center items-center">
                        <img class="h-40 w-40 rounded-full" :src="userInfo?.picture!" alt="">
                    </div>
                    <div class="flex justify-center items-center">
                        <h1 class="text-2xl font-bold">{{ userInfo?.name }}</h1>
                    </div>
                    <ion-button fill="outline" @click="onClickSignOut" class="w-1/2 mt-[50px]">登出</ion-button>
                </div>
                <div v-else class="flex flex-col justify-center items-center">
                    <div class="flex justify-center items-center">
                        <h1 class="text-2xl font-bold">未登入</h1>
                    </div>
                    <ion-button fill="outline" @click="onClickSignIn" class="w-1/2 mt-[50px]">登录</ion-button>
                </div>
            </div>
        </ion-content>
    </ion-page>
</template>
  
<script setup lang="ts">
import { IonContent, IonPage, IonButton, IonHeader, IonProgressBar, IonToolbar, IonTitle } from '@ionic/vue';
import { useLogto, UserInfoResponse } from "@logto/vue";
import { onMounted, ref } from "vue";
import { useAccountStore } from "@/stores/account"

const store = useAccountStore()
const isLoadFinished = ref(false)
const { signOut, signIn, isAuthenticated, fetchUserInfo, getIdTokenClaims } = useLogto();
const onClickSignOut = () => {
    signOut('http://localhost:8081');
    store.token = ""
}
const onClickSignIn = () => signIn('http://localhost:8081/callback');

const userInfo = ref<UserInfoResponse>()
onMounted(async () => {
    if (isAuthenticated) {
        userInfo.value = await fetchUserInfo()
        console.log((await getIdTokenClaims())?.roles)
        console.log(userInfo.value)
        isLoadFinished.value = true
    }
})

</script>