export interface IProductByBooking {
    id?: number;
    productId?: number;
    bookingId?: number;
}

export class ProductByBooking implements IProductByBooking {
    constructor(public id?: number, public productId?: number, public bookingId?: number) {}
}
