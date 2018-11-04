export interface IProviderIm {
    id?: number;
    name?: string;
    companyId?: number;
}

export class ProviderIm implements IProviderIm {
    constructor(public id?: number, public name?: string, public companyId?: number) {}
}
