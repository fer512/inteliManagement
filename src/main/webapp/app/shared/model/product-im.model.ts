export interface IProductIm {
    id?: number;
    name?: string;
    providerId?: number;
}

export class ProductIm implements IProductIm {
    constructor(public id?: number, public name?: string, public providerId?: number) {}
}
