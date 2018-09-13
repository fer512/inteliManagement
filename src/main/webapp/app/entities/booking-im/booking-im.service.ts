import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBookingIm } from 'app/shared/model/booking-im.model';

type EntityResponseType = HttpResponse<IBookingIm>;
type EntityArrayResponseType = HttpResponse<IBookingIm[]>;

@Injectable({ providedIn: 'root' })
export class BookingImService {
    private resourceUrl = SERVER_API_URL + 'api/bookings';

    constructor(private http: HttpClient) {}

    create(booking: IBookingIm): Observable<EntityResponseType> {
        return this.http.post<IBookingIm>(this.resourceUrl, booking, { observe: 'response' });
    }

    update(booking: IBookingIm): Observable<EntityResponseType> {
        return this.http.put<IBookingIm>(this.resourceUrl, booking, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBookingIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBookingIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    findByCriteria(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.post<IBookingIm[]>(this.resourceUrl + '/findByCriteria', req, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
