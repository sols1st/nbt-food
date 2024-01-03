<template>
    <ion-page mode="ios">
        <ion-header v-if="isLoadFinished">
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button text="返回" default-href="/list"></ion-back-button>
                </ion-buttons>
                <ion-title>{{ restaurant.name }}</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-header v-else>
            <ion-toolbar>
                <ion-title>加载中</ion-title>
                <ion-progress-bar type="indeterminate"></ion-progress-bar>
            </ion-toolbar>
        </ion-header>

        <ion-content v-if="isLoadFinished">
            <div class="flex flex-col px-[20px] pt-[20px] items-start gap-[20px] self-stretch">
                <div class="flex items-start gap-[16px] self-stretch">
                    <div style="border: 1px solid rgba(0, 0, 0, 0.25);"
                        class="w-[118px] h-[118px] flex-shrink-0 rounded-[30px] overflow-hidden">
                        <img src="../assets/jysb.png" class="w-full h-auto">
                    </div>
                    <div class="flex flex-col items-start gap-[11px] self-stretch">
                        <div class="flex flex-col items-start flex-grow self-stretch">
                            <div class="flex items-start self-stretch">
                                <a class="text-textBlack-light text-[22px] font-semibold leading-[23px] tracking-wider">
                                    {{ restaurant.name }}
                                </a>
                            </div>
                            <div class="flex items-start gap-[10px] self-stretch">
                                <a class="text-systemGrey-light text-[11px] font-normal leading-normal tracking-wider">
                                    位置:{{
                                        restaurant.locationName }}</a>
                            </div>
                            <div class="flex items-start gap-[10px] self-stretch">
                                <a class="text-systemGrey-light text-[11px] font-normal leading-normal tracking-wider"> {{
                                    restaurant.description }}</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="w-full h-[0.5px] bg-separatorColor-light-withTrans"></div>

            </div>
            <div class="flex flex-col px-[19px] pt-[16px] items-start gap-y-[14px]">
                <div class="flex items-center gap-[16px] self-stretch">
                    <div class="flex flex-col justify-center items-start flex-grow flex-shrink-0 basis-0">
                        <a class="text-textBlack-light font-semibold text-[17px] leading-normal tracking-[0.68px]">评分及评论</a>
                    </div>
                    <button class="flex p-[3px] items-start bg-systemWhite-light">
                        <a class="text-systemBlue-light text-[14px] leading-normal tracking-[-0.14px] font-normal">查看全部</a>
                    </button>
                </div>
                <div class="flex items-center gap-[16px] self-stretch">
                    <div class="flex flex-col justify-center items-center gap-[4px]">
                        <div class="flex items-start">
                            <a
                                class=" text-rateColor-light-rate text-[60px] font-bold leading-[48px] tracking-[-1.8px]">4.7</a>
                        </div>
                        <div class="flex items-start">
                            <a
                                class=" flex items-start text-rateColor-light-label text-[15px] font-bold tracking-widest">满分5分</a>
                        </div>
                    </div>
                    <div class="flex flex-col items-end gap-[4px] flex-grow">
                        <!-- 星级表 -->
                    </div>
                </div>
                <div class="w-full h-[0.5px] bg-separatorColor-light-withTrans"></div>
                <div class="flex items-center gap-[16px] self-stretch">
                    <div class="flex items-start flex-grow">
                        <a style="font-family: SF Pro Text;"
                            class="text-rateColor-light-label text-[14px] font-medium tracking-wider">轻点评分：</a>
                    </div>
                    <div class="flex items-start gap-[20px]">
                        <svg class="w-[24px] h-[23px]" v-for="_ in [1, 1, 1, 1, 1]" xmlns="http://www.w3.org/2000/svg"
                            width="26" height="25" viewBox="0 0 26 25" fill="none">
                            <path d="M9.5 8.5L13 1L16.5 8.5H25L18.5 15L20.5 24L13 19L5.5 24L7.5 15L1 8.5H9.5Z"
                                stroke="#377CF6" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                        </svg>
                    </div>
                </div>
                <div class="flex flex-col w-[100%] self-center gap-[14px] mb-[20px]">
                    <CommentCard :content="item.content" :user-name="item.userName" :time="item.gmtCreate"
                        v-for="item in comments"></CommentCard>
                    <a v-if="!hasComment" class="text-rateColor-light-label text-[14px] font-medium tracking-wider self-center mt-[60px]">暂无评论</a>
                </div>
            </div>
        </ion-content>
        <ion-content v-else>

        </ion-content>
    </ion-page>
</template>
  
<script setup lang="ts">
import { ref } from 'vue';
import { IonHeader, IonToolbar, IonButtons, IonBackButton, IonProgressBar, IonTitle, IonContent, IonPage } from '@ionic/vue';
import CommentCard from '@/components/CommentCard.vue';
import { Restaurant } from '@/models/restaurant';
import RestaurantService from '@/services/restaurant';
import { Comment } from '@/models/comment';
import CommentService from '@/services/comment';
import { useRoute } from 'vue-router';
import { CommentPage } from '@/models/page';
const route = useRoute();
const restaurantId = route.params.id as string;
const isLoadFinished = ref(false)
const hasComment = ref(false)


const restaurant = ref({} as Restaurant);
const comments = ref([] as Comment[]);

const getDetail = async () => {
    restaurant.value = await RestaurantService.queryRestaurant(restaurantId);
    const page: CommentPage = {
        currentPage: 1,
        pageSize: 10,
        queryParams: {
            restaurantId: eval(restaurantId)
        }
    }
    const res = await CommentService.pageComment(page)
    hasComment.value = res.totalRecords > 0
    comments.value = res.records
    isLoadFinished.value = true
}
getDetail()



</script>
  
<style scoped></style>