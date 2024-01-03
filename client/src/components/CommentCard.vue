<script setup lang="ts">
// import { ref } from "vue";
// import { Comment } from '@/models/comment';
const props = defineProps(['title','content','time','userName'])
</script>
<template>
    <div
        class="rounded-[8px]  bg-systemBackground-lightSecondary flex py-[12px] px-[20px] flex-col items-start gap-[16px] min-h-[150px]">
        <div class="flex flex-col items-start gap-[8px] w-full">
            <div class="flex items-start gap-[16px] self-stretch">
                <div class="flex flex-col items-start gap-[2px] flex-grow">
                    <div class="flex flex-col items-center self-stretch">
                        <a style="font-family: SF Pro Text;"
                            class="self-stretch text-textBlack-light text-[12px] font-semibold leading-[16px] tracking-wider">{{ props.title }}</a>
                    </div>
                    <div class="flex items-center gap-[3px]">
                        <svg v-for="_ in [1, 1, 1, 1, 1]" class="w-[11.5px] h-[11.5px]" xmlns="http://www.w3.org/2000/svg"
                            width="12" height="12" viewBox="0 0 12 12" fill="none">
                            <g clip-path="url(#clip0_309_1719)">
                                <path
                                    d="M7.52325 4.02053L6.45054 0.515917C6.23892 -0.171972 5.26108 -0.171972 5.05675 0.515917L3.97675 4.02053H0.729439C0.0215981 4.02053 -0.270295 4.92565 0.306194 5.33114L2.96242 7.21379L1.9189 10.5519C1.70728 11.2253 2.49539 11.7683 3.05729 11.3411L5.75 9.31366L8.44271 11.3484C9.00461 11.7756 9.79272 11.2325 9.5811 10.5591L8.53758 7.22103L11.1938 5.33838C11.7703 4.92565 11.4784 4.02777 10.7706 4.02777H7.52325V4.02053Z"
                                    fill="#EE8834" />
                            </g>
                            <defs>
                                <clipPath id="clip0_309_1719">
                                    <rect width="11.5" height="11.5" fill="white" />
                                </clipPath>
                            </defs>
                        </svg>
                    </div>
                </div>
                <div class="flex flex-col items-end gap-[4px]">
                    <div class="flex flex-col items-center">
                        <a class="text-systemGrey-light text-right text-[12px] font-normal">{{ props.time }}</a>
                    </div>
                    <div class="flex flex-col items-center">
                        <a class="text-systemGrey-light text-right text-[12px] font-normal">{{ props.userName }}</a>
                    </div>
                </div>
            </div>
            <div class="h-[51px] self-stretch">
                <div class="flex w-[295px] flex-col items-center">
                    <a class="self-stretch font-normal text-textBlack-light text-[12px] leading-[138.7%] tracking-normal">
                        {{ props.content }}
                    </a>
                </div>
                <a class="text-systemBlue-light text-[11px] font-normal leading-normal">更多</a>
            </div>

        </div>
    </div>
</template>