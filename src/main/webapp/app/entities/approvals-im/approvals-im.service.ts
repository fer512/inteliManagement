import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApprovalsIm } from 'app/shared/model/approvals-im.model';

type EntityResponseType = HttpResponse<IApprovalsIm>;
type EntityArrayResponseType = HttpResponse<IApprovalsIm[]>;

@Injectable({ providedIn: 'root' })
export class ApprovalsImService {
    private resourceUrl = SERVER_API_URL + 'api/approvals';

    constructor(private http: HttpClient) {}

    create(approvals: IApprovalsIm): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(approvals);
        return this.http
            .post<IApprovalsIm>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(approvals: IApprovalsIm): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(approvals);
        return this.http
            .put<IApprovalsIm>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IApprovalsIm>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IApprovalsIm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(approvals: IApprovalsIm): IApprovalsIm {
        const copy: IApprovalsIm = Object.assign({}, approvals, {
            stastDate: approvals.stastDate != null && approvals.stastDate.isValid() ? approvals.stastDate.toJSON() : null,
            endDate: approvals.endDate != null && approvals.endDate.isValid() ? approvals.endDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.stastDate = res.body.stastDate != null ? moment(res.body.stastDate) : null;
        res.body.endDate = res.body.endDate != null ? moment(res.body.endDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((approvals: IApprovalsIm) => {
            approvals.stastDate = approvals.stastDate != null ? moment(approvals.stastDate) : null;
            approvals.endDate = approvals.endDate != null ? moment(approvals.endDate) : null;
        });
        return res;
    }
}
