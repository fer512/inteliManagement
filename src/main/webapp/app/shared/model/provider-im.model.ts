import { IProductIm } from 'app/shared/model//product-im.model';

export interface IProviderIm {
    id?: number;
    name?: string;
    email?: string;
    addressId?: number;
    products?: IProductIm[];
    companyId?: number;
}

export class ProviderIm implements IProviderIm {
    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
        public addressId?: number,
        public products?: IProductIm[],
        public companyId?: number
    ) {}
}
