import { IProvinceIm } from 'app/shared/model//province-im.model';

export interface ICountryIm {
    id?: number;
    name?: string;
    provinces?: IProvinceIm[];
}

export class CountryIm implements ICountryIm {
    constructor(public id?: number, public name?: string, public provinces?: IProvinceIm[]) {}
}
