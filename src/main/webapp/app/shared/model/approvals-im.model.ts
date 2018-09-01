import { Moment } from 'moment';

export const enum ApprovalsStatusType {
    APPOVED = 'APPOVED',
    REJECTED = 'REJECTED',
    PENDING = 'PENDING'
}

export interface IApprovalsIm {
    id?: number;
    stastDate?: Moment;
    endDate?: Moment;
    status?: ApprovalsStatusType;
}

export class ApprovalsIm implements IApprovalsIm {
    constructor(public id?: number, public stastDate?: Moment, public endDate?: Moment, public status?: ApprovalsStatusType) {}
}
