import { IProviderIm } from 'app/shared/model//provider-im.model';
import { IProductByBooking } from 'app/shared/model//product-by-booking.model';

export interface IProductIm {
    id?: number;
    name?: string;
    code?: string;
    product_by_providers?: IProviderIm[];
    bookings?: IProductByBooking[];
}

export class ProductIm implements IProductIm {
    constructor(
        public id?: number,
        public name?: string,
        public code?: string,
        public product_by_providers?: IProviderIm[],
        public bookings?: IProductByBooking[]
    ) {}
}
