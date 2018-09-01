export interface IBookingIm {
    id?: number;
    idTransaction?: string;
    idReserveLocatorJuniper?: string;
    idReserveLocatorExternal?: string;
    detail?: string;
    companyId?: number;
}

export class BookingIm implements IBookingIm {
    constructor(
        public id?: number,
        public idTransaction?: string,
        public idReserveLocatorJuniper?: string,
        public idReserveLocatorExternal?: string,
        public detail?: string,
        public companyId?: number
    ) {}
}
