/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { CompanyImDetailComponent } from 'app/entities/company-im/company-im-detail.component';
import { CompanyIm } from 'app/shared/model/company-im.model';

describe('Component Tests', () => {
    describe('CompanyIm Management Detail Component', () => {
        let comp: CompanyImDetailComponent;
        let fixture: ComponentFixture<CompanyImDetailComponent>;
        const route = ({ data: of({ company: new CompanyIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [CompanyImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CompanyImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CompanyImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.company).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
