import { IEmployeeIm } from 'app/shared/model//employee-im.model';
import { IBookingIm } from 'app/shared/model//booking-im.model';
import { IProviderIm } from 'app/shared/model//provider-im.model';

export interface ICompanyIm {
    id?: number;
    name?: string;
    email?: string;
    actived?: string;
    img?: string;
    addressId?: number;
    employees?: IEmployeeIm[];
    bookings?: IBookingIm[];
    providers?: IProviderIm[];
}

export class CompanyIm implements ICompanyIm {
    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
        public actived?: string,
        public img?: string,
        public addressId?: number,
        public employees?: IEmployeeIm[],
        public bookings?: IBookingIm[],
        public providers?: IProviderIm[]
    ) {}
}
