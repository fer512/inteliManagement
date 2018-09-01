export interface IProvinceIm {
    id?: number;
    name?: string;
    countryId?: number;
}

export class ProvinceIm implements IProvinceIm {
    constructor(public id?: number, public name?: string, public countryId?: number) {}
}
