export const enum PhoneType {
    CELL_PHONE = 'CELL_PHONE',
    HOME_PHONE = 'HOME_PHONE',
    WORK_PHONE = 'WORK_PHONE'
}

export interface IPhoneIm {
    id?: number;
    type?: PhoneType;
    numpber?: string;
    employeeId?: number;
}

export class PhoneIm implements IPhoneIm {
    constructor(public id?: number, public type?: PhoneType, public numpber?: string, public employeeId?: number) {}
}
