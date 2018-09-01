import { Moment } from 'moment';

export interface INotificationIm {
    id?: number;
    name?: string;
    stastDate?: Moment;
    endDate?: Moment;
    view?: boolean;
    employeeId?: number;
}

export class NotificationIm implements INotificationIm {
    constructor(
        public id?: number,
        public name?: string,
        public stastDate?: Moment,
        public endDate?: Moment,
        public view?: boolean,
        public employeeId?: number
    ) {
        this.view = this.view || false;
    }
}
