export interface IProviderIm {
    id?: number;
    name?: string;
    email?: string;
    addressId?: number;
    companyId?: number;
}

export class ProviderIm implements IProviderIm {
    constructor(public id?: number, public name?: string, public email?: string, public addressId?: number, public companyId?: number) {}
}
