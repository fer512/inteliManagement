import { IProviderIm } from 'app/shared/model//provider-im.model';
import { IProductByBooking } from 'app/shared/model//product-by-booking.model';

export interface IProductIm {
    id?: number;
    name?: string;
    code?: string;
}

export class ProductIm implements IProductIm {
    constructor(public id?: number, public name?: string, public code?: string) {}
}
