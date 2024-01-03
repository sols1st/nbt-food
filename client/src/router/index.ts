import { createRouter, createWebHistory } from "@ionic/vue-router";
import { decodeAccessToken } from '@logto/js'
import { useAccountStore } from '@/stores/account';
import { RouteRecordRaw } from "vue-router";
import { useLogto} from "@logto/vue";
import TabBar from "@/components/TapBar.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "list",
    redirect: "/list",
  },
  {
    path: "/detail/:id",
    component: () => import("@/views/DetailPage.vue"),
  },
  {
    path: "/callback",
    name: "callback",
    component: () => import("@/views/CallbackView.vue"),
  },
  {
    path: "/",
    component: TabBar,
    children: [
      {
        path: "",
        redirect: "/list",
      },
      {
        path: "random",
        component: () => import("@/views/RandomPage.vue"),
      },
      {
        path: "list",
        component: () => import("@/views/ListPage.vue"),
      },
      {
        path: "login",
        component: () => import("@/views/LoginPage.vue"),
      },
      {
        path: "manage",
        component: () => import("@/views/ManagePage.vue"),
        meta: {
          requiresAuth: true,
          requiredScopes: ['admin'],
        },
      },
      {
        path: "manage/restaurant",
        component: () => import("@/views/RestaurantManage.vue"),
        meta: {
          requiresAuth: true,
          requiredScopes: ['admin'],
        },
      },
      {
        path: "manage/user",
        component: () => import("@/views/UserManage.vue"),
        meta: {
          requiresAuth: true,
          requiredScopes: ['admin'],
        },
      },
      {
        path: "manage/location",
        component: () => import("@/views/LocationManage.vue"),
        meta: {
          requiresAuth: true,
          requiredScopes: ['admin'],
        },
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// const { isAuthenticated } = useLogto();
// const store = useAccountStore()
// const scoop = decodeAccessToken(store.token).scope

// router.beforeEach((to, from, next) => {

//   const hasRequiredScopes = scoop == "admin"

//   if (to.meta.requiresAuth && !isAuthenticated) {
//     next('/login');
//   } else if (
//     to.meta.requiredScopes &&
//     !hasRequiredScopes
//   ) {
//     next('/login');
//   } else {
//     next();
//   }
// });

export default router;
