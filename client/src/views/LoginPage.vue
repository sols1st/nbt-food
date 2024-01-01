<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-title>个人中心</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <div v-if="isAuthenticated">
                <div>Signed in</div>
                <button @click="onClickSignOut">Sign Out</button>
                <div class="flex justify-center items-center">
                    <img class="h-40 w-40 rounded-full" :src="userInfo?.picture" alt="">
                </div>
                {{ userInfo }}
            </div>
            <div v-else>
                <button @click="onClickSignIn">Sign In</button>
            </div>
        </ion-content>
    </ion-page>
</template>
  
<script setup lang="ts">
import { IonContent, IonPage, IonHeader, IonToolbar, IonTitle } from '@ionic/vue';
import { useLogto, UserInfoResponse } from "@logto/vue";
import { onMounted, ref } from "vue";

const { signOut, signIn, isAuthenticated, fetchUserInfo, getAccessToken } = useLogto();
const onClickSignOut = () => signOut('http://localhost:8081');
const onClickSignIn = () => signIn('http://localhost:8081/callback');
// const token

const userInfo = ref<UserInfoResponse>()
onMounted(async () => {
    userInfo.value = await fetchUserInfo()
    console.log(await getAccessToken("https://food.app.nbtca.space"))
})

</script>