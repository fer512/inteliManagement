import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProductByBooking } from 'app/shared/model/product-by-booking.model';

type EntityResponseType = HttpResponse<IProductByBooking>;
type EntityArrayResponseType = HttpResponse<IProductByBooking[]>;

@Injectable({ providedIn: 'root' })
export class ProductByBookingService {
    private resourceUrl = SERVER_API_URL + 'api/product-by-bookings';

    constructor(private http: HttpClient) {}

    create(productByBooking: IProductByBooking): Observable<EntityResponseType> {
        return this.http.post<IProductByBooking>(this.resourceUrl, productByBooking, { observe: 'response' });
    }

    update(productByBooking: IProductByBooking): Observable<EntityResponseType> {
        return this.http.put<IProductByBooking>(this.resourceUrl, productByBooking, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProductByBooking>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProductByBooking[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
