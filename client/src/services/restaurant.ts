import Axios from "@/utils/axios";
import { Restaurant, RestaurantLocation } from "@/models/restaurant";
import {RestaurantPage} from "@/models/page";
import Toast from "@/utils/toast";

const RestaurantService = {
  async listRestaurantGroupByLocation() {
    const res = await Axios("/restaurant/list", null, "GET");
    return res as RestaurantLocation[];
  },
  async addRestaurant(r: Restaurant) {
    const res = await Axios("/restaurant/add", r, "post") as string
    Toast(res)
    return res
  },
  async removeRestaurant(r: Restaurant) {
    const res = await Axios("/restaurant/delete/" + r.id, null, "delete") as string
    Toast(res)
    return res
  },
  async updateRestaurant(r: Restaurant) {
    const res = await Axios("/restaurant/update", r, "put") as string
    Toast(res)
    return res
  },
  // 分页条件查询
  async pageRestaurant(body:RestaurantPage) {
    const res = await Axios("/restaurant/page", body, "post") as Restaurant[]
    return res
  },
  async randomRestaurant() {
    const res = await Axios("/restaurant/random", null, "GET") as Restaurant
    return res
  }
};

export default RestaurantService;
