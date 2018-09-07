/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { VariationDetailComponent } from 'app/entities/variation/variation-detail.component';
import { Variation } from 'app/shared/model/variation.model';

describe('Component Tests', () => {
    describe('Variation Management Detail Component', () => {
        let comp: VariationDetailComponent;
        let fixture: ComponentFixture<VariationDetailComponent>;
        const route = ({ data: of({ variation: new Variation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [VariationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VariationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VariationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.variation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
