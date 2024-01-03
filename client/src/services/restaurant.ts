import Axios from "@/utils/axios";
import { Restaurant, RestaurantLocation } from "@/models/restaurant";
import {RestaurantPage} from "@/models/page";

const RestaurantService = {
  async listRestaurantGroupByLocation() {
    return await Axios("/restaurant/list", null, "GET",false) as RestaurantLocation[];
  },
  async addRestaurant(r: Restaurant) {
    return await Axios("/restaurant/add", r, "post") as string
  },
  async removeRestaurant(r: Restaurant) {
    return await Axios("/restaurant/delete/" + r.id, null, "delete") as string
  },
  async updateRestaurant(r: Restaurant) {
    return await Axios("/restaurant/update", r, "put") as string
  },
  // 分页条件查询
  async pageRestaurant(body:RestaurantPage) {
    return await Axios("/restaurant/page", body, "post",false) as Restaurant[]
  },
  async queryRestaurant(id:string) {
    return await Axios("/restaurant/query/"+id, null, "get",false) as Restaurant
  },
  async randomRestaurant() {
    return await Axios("/restaurant/random", null, "GET",false) as Restaurant
  }
};

export default RestaurantService;
