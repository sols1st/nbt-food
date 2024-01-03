<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useLogto, UserInfoResponse, useHandleSignInCallback } from "@logto/vue";
import { useAccountStore } from "@/stores/account"
const { isAuthenticated, getAccessToken, fetchUserInfo } = useLogto();


const store = useAccountStore()
const router = useRouter()
const { isLoading } = useHandleSignInCallback(async () => {
    if (isAuthenticated) {
        const token = await getAccessToken("https://food.app.nbtca.space")
        store.token = token as string
        store.account = await fetchUserInfo() as UserInfoResponse
        router.push("/login")
    }
});
</script>

<template>
    <p v-if="isLoading">Redirecting...</p>
</template>