import { Location } from "./location";
interface Restaurant {
  id?: number;
  name?: string;
  pic?: string;
  userId?: number;
  description?: string;
  locationId?: number;
  locationName?: string;
}

interface RestaurantLocation {
  location?: Location;
  restaurantList?: Restaurant[];
}

export type { Restaurant, RestaurantLocation };
