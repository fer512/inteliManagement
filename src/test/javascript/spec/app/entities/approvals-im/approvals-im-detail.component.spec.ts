/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ApprovalsImDetailComponent } from 'app/entities/approvals-im/approvals-im-detail.component';
import { ApprovalsIm } from 'app/shared/model/approvals-im.model';

describe('Component Tests', () => {
    describe('ApprovalsIm Management Detail Component', () => {
        let comp: ApprovalsImDetailComponent;
        let fixture: ComponentFixture<ApprovalsImDetailComponent>;
        const route = ({ data: of({ approvals: new ApprovalsIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ApprovalsImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ApprovalsImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApprovalsImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.approvals).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
