import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVariation } from 'app/shared/model/variation.model';

type EntityResponseType = HttpResponse<IVariation>;
type EntityArrayResponseType = HttpResponse<IVariation[]>;

@Injectable({ providedIn: 'root' })
export class VariationService {
    private resourceUrl = SERVER_API_URL + 'api/variations';
    private urlCreateVariation = SERVER_API_URL + 'api/createVariation';

    constructor(private http: HttpClient) {}

    create(variation: IVariation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(variation);
        return this.http
            .post<IVariation>(this.urlCreateVariation, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(variation: IVariation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(variation);
        return this.http
            .put<IVariation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVariation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVariation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(variation: IVariation): IVariation {
        const copy: IVariation = Object.assign({}, variation, {
            creation_date: variation.creation_date != null && variation.creation_date.isValid() ? variation.creation_date.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.creation_date = res.body.creation_date != null ? moment(res.body.creation_date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((variation: IVariation) => {
            variation.creation_date = variation.creation_date != null ? moment(variation.creation_date) : null;
        });
        return res;
    }
}
