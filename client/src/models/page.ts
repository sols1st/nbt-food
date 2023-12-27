interface Page<T> {
  currentPage?: number;
  pageSize?: number;
  queryParams?: T;
}

interface RestaurantPage
  extends Page<{
    name?: string;
    restaurantId?: number;
    locationId?: number;
  }> {}

export type { RestaurantPage };
