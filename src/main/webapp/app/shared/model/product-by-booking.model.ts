export interface IProductByBooking {
    id?: number;
    productId?: number;
    bookingId?: number;
    idReserveLocatorJuniperProduct?: string;
    idReserveLocatorJuniper?: string;
    idReserveLocatorExternal?: string;
}

export class ProductByBooking implements IProductByBooking {
    constructor(
        public id?: number,
        public productId?: number,
        public bookingId?: number,
        public idReserveLocatorJuniperProduct?: string,
        public idReserveLocatorJuniper?: string,
        public idReserveLocatorExternal?: string
    ) {}
}
