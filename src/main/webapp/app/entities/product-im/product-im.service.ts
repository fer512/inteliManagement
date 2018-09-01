import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProductIm } from 'app/shared/model/product-im.model';

type EntityResponseType = HttpResponse<IProductIm>;
type EntityArrayResponseType = HttpResponse<IProductIm[]>;

@Injectable({ providedIn: 'root' })
export class ProductImService {
    private resourceUrl = SERVER_API_URL + 'api/products';

    constructor(private http: HttpClient) {}

    create(product: IProductIm): Observable<EntityResponseType> {
        return this.http.post<IProductIm>(this.resourceUrl, product, { observe: 'response' });
    }

    update(product: IProductIm): Observable<EntityResponseType> {
        return this.http.put<IProductIm>(this.resourceUrl, product, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProductIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProductIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
