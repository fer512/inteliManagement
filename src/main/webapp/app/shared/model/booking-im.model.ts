import { IProductByBooking } from 'app/shared/model//product-by-booking.model';

export interface IBookingIm {
    id?: number;
    companyId?: number;
    idTransaction?: string;
    juniperReservationCost?: number;
    juniperSalePrice?: number;
    paymentCreditCard?: number;
    paymentPointsInUSD?: number;
    paymentType?: string;
    products?: IProductByBooking[];
    detail?: string;
    email?: string;
    benefitInReservation?: number;
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
