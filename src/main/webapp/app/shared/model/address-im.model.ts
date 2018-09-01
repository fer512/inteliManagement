export interface IAddressIm {
    id?: number;
    country?: string;
    province?: string;
    locality?: string;
    street?: string;
    postalCode?: string;
    locationX?: string;
    locationY?: string;
}

export class AddressIm implements IAddressIm {
    constructor(
        public id?: number,
        public country?: string,
        public province?: string,
        public locality?: string,
        public street?: string,
        public postalCode?: string,
        public locationX?: string,
        public locationY?: string
    ) {}
}
