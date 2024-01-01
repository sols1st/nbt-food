<script setup lang="ts">
import { useHandleSignInCallback } from '@logto/vue';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useLogto } from "@logto/vue";
const { signIn, isAuthenticated } = useLogto();
const router = useRouter()
const { isLoading } = useHandleSignInCallback(() => {
    // Navigate to root path when finished
});

onMounted(() => {
    if (isAuthenticated)
        router.push("/login")
})
</script>

<template>
    <!-- When it's working in progress -->
    <p v-if="isLoading">Redirecting...</p>
</template>