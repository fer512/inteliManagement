import { Moment } from 'moment';

export interface INotificationIm {
    id?: number;
    detail?: string;
    stastDate?: Moment;
    endDate?: Moment;
    creationDate?: Moment;
    view?: boolean;
}

export class NotificationIm implements INotificationIm {
    constructor(public id?: number, public stastDate?: Moment, public endDate?: Moment, public view?: boolean) {
        this.view = this.view || false;
    }
}
