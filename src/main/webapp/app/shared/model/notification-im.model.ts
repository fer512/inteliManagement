import { Moment } from 'moment';
import { User } from 'app/core';

export interface INotificationIm {
    id?: number;
    detail?: string;
    stastDate?: Moment;
    endDate?: Moment;
    creationDate?: Moment;
    view?: boolean;
    type?: string;
    userCreation?: User;
    idReference?: string;
}

export class NotificationIm implements INotificationIm {
    constructor(
        public id?: number,
        public stastDate?: Moment,
        public endDate?: Moment,
        public view?: boolean,
        public type?: string,
        public userCreation?: User,
        public idReference?: string
    ) {
        this.view = this.view || false;
    }
}
