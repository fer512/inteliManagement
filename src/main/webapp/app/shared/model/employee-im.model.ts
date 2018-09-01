import { IPhoneIm } from 'app/shared/model//phone-im.model';
import { INotificationIm } from 'app/shared/model//notification-im.model';

export interface IEmployeeIm {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    addressId?: number;
    userId?: number;
    phones?: IPhoneIm[];
    notifications?: INotificationIm[];
    managerId?: number;
    companyId?: number;
}

export class EmployeeIm implements IEmployeeIm {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public addressId?: number,
        public userId?: number,
        public phones?: IPhoneIm[],
        public notifications?: INotificationIm[],
        public managerId?: number,
        public companyId?: number
    ) {}
}
