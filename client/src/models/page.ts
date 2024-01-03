interface Page<T> {
  currentPage: number;
  pageSize: number;
  queryParams: T;
}

interface RestaurantPage
  extends Page<{
    name?: string;
    restaurantId?: number;
    locationId?: number;
  }> {}

interface CommentPage
  extends Page<{
    id?: number;
    restaurantId?: number;
  }> {}

export type { RestaurantPage,CommentPage };
