import { IProductByBooking } from 'app/shared/model//product-by-booking.model';

export interface IBookingIm {
    id?: number;
    idTransaction?: string;
    idReserveLocatorJuniper?: string;
    idReserveLocatorExternal?: string;
    detail?: string;
    paymentType?: string;
    paymentCreditCard?: number;
    paymentPointsInUSD?: number;
    juniperSalePrice?: number;
    juniperReservationCost?: number;
    benefitInReservation?: number;
    companyId?: number;
    products?: IProductByBooking[];
}

export class BookingIm implements IBookingIm {
    constructor(
        public id?: number,
        public idTransaction?: string,
        public idReserveLocatorJuniper?: string,
        public idReserveLocatorExternal?: string,
        public detail?: string,
        public paymentType?: string,
        public paymentCreditCard?: number,
        public paymentPointsInUSD?: number,
        public juniperSalePrice?: number,
        public juniperReservationCost?: number,
        public benefitInReservation?: number,
        public companyId?: number,
        public products?: IProductByBooking[]
    ) {}
}
